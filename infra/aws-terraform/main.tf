terraform {
    required_providers {
        aws = {
            source = "hashicorp/aws"
            version = "5.65.0"
        }
    }
    required_version = "1.11.4"
}
# This Terraform configuration file creates an EC2 instance and an RDS instance in AWS.
# It also sets up security groups to allow HTTP, SSH, and MySQL traffic.
# The EC2 instance is provisioned with Docker and the RDS instance is configured with a MySQL database.
# The configuration uses variables for instance type, key name, and database credentials.
# The AWS provider is configured to use a specific region and credentials file.
# The EC2 instance is tagged with a name for easy identification.
data "aws_subnets" "default" {
  filter {
    name   = "default-for-az"
    values = ["true"]
  }
}

provider "aws" {
  region                   = "us-east-1"
  shared_credentials_files = ["./.secrets/credentials"]
  profile                  = "joelpangop-schoolspace"
}

resource "aws_instance" "school_space_server" {
  ami                         = "ami-0c02fb55956c7d316" # Amazon Linux 2
  instance_type               = "t2.micro"
  key_name                    = "devops-joel"
  associate_public_ip_address = true
  vpc_security_group_ids = [aws_security_group.allow_http_ssh.id]

  tags = {
    Name = "SchoolSpaceAppEC2"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo yum update -y",
      "sudo yum install docker -y",
      "sudo systemctl start docker",
      "sudo usermod -aG docker ec2-user"
    ]

    connection {
      type        = "ssh"
      user        = "ec2-user"
      private_key = file(var.private_key_path)
      host        = self.public_ip
    }
  }
}

resource "random_id" "suffix" {
  byte_length = 4
}

resource "aws_vpc" "main" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = {
    Name = "main-vpc"
  }
}

resource "aws_security_group" "allow_http_ssh" {
  name        = "allow_mysql_${random_id.suffix.hex}"
  description = "Allow SSH (22), HTTP (80), 8080, 3000"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 3000
    to_port     = 3000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  
  lifecycle {
    create_before_destroy = true
  }
}