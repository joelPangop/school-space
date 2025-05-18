output "instance_public_ip" {
  value = aws_instance.school_space_server.public_ip
}

output "rds_endpoint" {
  value = aws_db_instance.schoolspace_db.endpoint
}