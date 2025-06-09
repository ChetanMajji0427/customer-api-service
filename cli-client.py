import requests
import argparse
import uuid

BASE_URL = "http://localhost:8080/api/customers"

def create_customer():
    first_name = input("First name: ")
    last_name = input("Last name: ")
    email = input("Email: ")
    phone = input("Phone: ")

    payload = {
        "firstName": first_name,
        "lastName": last_name,
        "email": email,
        "phoneNumber": phone
    }
    response = requests.post(BASE_URL, json=payload)
    print(response.json())

def list_customers():
    response = requests.get(BASE_URL)
    for cust in response.json():
        print(f"{cust['id']}: {cust['firstName']} {cust['lastName']} <{cust['email']}>")

def get_customer(cust_id):
    response = requests.get(f"{BASE_URL}/{cust_id}")
    print(response.json())

def delete_customer(cust_id):
    response = requests.delete(f"{BASE_URL}/{cust_id}")
    print("Deleted" if response.status_code == 204 else response.text)

if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument("action", choices=["create", "list", "get", "delete"])
    parser.add_argument("--id", help="Customer ID for get/delete")
    args = parser.parse_args()

    if args.action == "create":
        create_customer()
    elif args.action == "list":
        list_customers()
    elif args.action == "get" and args.id:
        get_customer(args.id)
    elif args.action == "delete" and args.id:
        delete_customer(args.id)
    else:
        print("Missing required --id for get/delete")
