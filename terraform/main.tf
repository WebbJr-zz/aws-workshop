terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "3.14.1"
    }
  }
}

provider "aws" {
  region = "eu-central-1"
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_ecr_repository" "this" {
  name = "aws-workshop-terraform-new"
}