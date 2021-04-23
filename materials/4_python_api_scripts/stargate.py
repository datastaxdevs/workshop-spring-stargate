import requests

url = "https://eabba33b-dc22-4055-bced-e84e87cfa92c-europe-west1.apps.astra.datastax.com/api/rest/v1/keyspaces"

headers = {
    "accept": "application/json",
    "x-cassandra-token": "10916bf2-8254-43d9-846d-d8b96d2eb6ca"
}

response = requests.request("GET", url, headers=headers)

print(response.text)
