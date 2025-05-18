variable "instance_type" {
  description = "set aws instance type"
  type        = string
  default     = "t2.nano"
}

variable "aws_common_tags" {
  description = "set common tags for all aws resources"
  type        = map(any)
  default     = {
    Name        = "ec2-joeltraining"
  }
  
}

variable "key_name" {
  description = "devops-joel"
  type        = string
}

variable "private_key_path" {
  description = "Chemin local vers la clé privée .pem"
  type        = string
}

variable "db_name" {
  default = "schoolspacedb"
}

variable "db_user" {
  default = "root"
}

variable "db_password" {
  default = "Abc123..."
}