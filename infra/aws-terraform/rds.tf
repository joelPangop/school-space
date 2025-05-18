resource "aws_db_subnet_group" "schoolspace_db_subnet_group" {
  name       = "schoolspace-subnet-group"
  subnet_ids = data.aws_subnets.default.ids

  tags = {
    Name = "SchoolSpace RDS Subnet Group"
  }
}

resource "aws_db_instance" "schoolspace_db" {
  identifier         = "schoolspace-db"
  engine             = "mysql"
  instance_class     = "db.t3.micro"
  allocated_storage  = 20
  db_name            = var.db_name
  username           = var.db_user
  password           = var.db_password
  skip_final_snapshot = true
  publicly_accessible = true
  vpc_security_group_ids = [aws_security_group.allow_mysql.id]
  db_subnet_group_name   = aws_db_subnet_group.schoolspace_db_subnet_group.name

  tags = {
    Name = "SchoolSpaceRDS"
  }
}

resource "aws_security_group" "allow_mysql" {
  name        = "allow_mysql"
  description = "Allow MySQL traffic"
  ingress {
    from_port   = 3306
    to_port     = 3306
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # (en test uniquement ! à restreindre plus tard)
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}