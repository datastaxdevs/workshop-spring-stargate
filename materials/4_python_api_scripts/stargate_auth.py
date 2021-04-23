import requests

url = "https://eabba33b-dc22-4055-bced-e84e87cfa92c-europe-west1.apps.astra.datastax.com/api/rest/v1/auth"

payload = {
    "username": "gateuser",
    "password": "gatepassword"
}
headers = {
    "accept": "*/*",
    "content-type": "application/json"
}

response = requests.request("POST", url, json=payload, headers=headers)

print(response.text)
