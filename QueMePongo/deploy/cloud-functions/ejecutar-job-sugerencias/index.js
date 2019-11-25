const Buffer = require('safe-buffer').Buffer;
const Compute = require('@google-cloud/compute');
const compute = new Compute();

const zone = 'us-central1-a';

const vmConfig = {
    "kind": "compute#instance",
    "zone": "projects/que-me-pongo-259920/zones/us-central1-a",
    "machineType": "projects/que-me-pongo-259920/zones/us-central1-a/machineTypes/g1-small",
    "displayDevice": {
        "enableDisplay": false
    },
    "metadata": {
        "kind": "compute#metadata",
        "items": [
            {
                "key": "startup-script",
                "value": "sudo apt install default-jre\ngsutil cp gs://que-me-pongo-jobs/que-me-pongo-job-sugerencias.jar .\ngcloud logging write batch-execution \"Se va a ejecutar el job de sugerencias\"\njava -jar que-me-pongo-job-sugerencias.jar\ngcloud logging write batch-execution \"Se ejecutó el job de sugerencias\"\ngcp_zone=$(curl -H Metadata-Flavor:Google http://metadata.google.internal/computeMetadata/v1/instance/zone -s | cut -d/ -f4)\ngcloud compute instances delete $(hostname) --zone ${gcp_zone}"
            }
        ]
    },
    "tags": {
        "items": []
    },
    "disks": [
        {
            "kind": "compute#attachedDisk",
            "type": "PERSISTENT",
            "boot": true,
            "mode": "READ_WRITE",
            "autoDelete": true,
            "deviceName": "instance-1",
            "initializeParams": {
                "sourceImage": "projects/ubuntu-os-cloud/global/images/ubuntu-1804-bionic-v20191113",
                "diskType": "projects/que-me-pongo-259920/zones/us-central1-a/diskTypes/pd-standard",
                "diskSizeGb": "10"
            },
            "diskEncryptionKey": {}
        }
    ],
    "canIpForward": false,
    "networkInterfaces": [
        {
            "kind": "compute#networkInterface",
            "subnetwork": "projects/que-me-pongo-259920/regions/us-central1/subnetworks/default",
            "accessConfigs": [
                {
                    "kind": "compute#accessConfig",
                    "name": "External NAT",
                    "type": "ONE_TO_ONE_NAT",
                    "networkTier": "PREMIUM"
                }
            ],
            "aliasIpRanges": []
        }
    ],
    "description": "",
    "labels": {},
    "scheduling": {
        "preemptible": false,
        "onHostMaintenance": "MIGRATE",
        "automaticRestart": true,
        "nodeAffinities": []
    },
    "deletionProtection": false,
    "reservationAffinity": {
        "consumeReservationType": "ANY_RESERVATION"
    },
    "serviceAccounts": [
        {
            "email": "master@que-me-pongo-259920.iam.gserviceaccount.com",
            "scopes": [
                "https://www.googleapis.com/auth/cloud-platform"
            ]
        }
    ]
}



exports.createInstance = (event, context) => {
    const vmName = "instancia-job-sugerencias-" + Date.now();
    try {
        compute.zone(zone)
            .createVM(vmName, vmConfig)
            .then(data => {
                // Operation pending.
                const vm = data[0];
                const operation = data[1];
                console.log(`VM being created: ${vm.id}`);
                console.log(`Operation info: ${operation.id}`);
                return operation.promise();
            })
            .then(() => {
                const message = 'VM created with success, Cloud Function finished execution.';
                console.log(message);
            })
            .catch(err => {
                console.log(err);
            });
    } catch (err) {
        console.log(err);
    }
};