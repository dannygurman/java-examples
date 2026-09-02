# Router Rule Matcher — Candidate Guide

You build a packet filter.

A packet carries a source address, a destination address, a port and a protocol. 
A firewall rule matches packets and carries an action — `ALLOW` or `DENY`.
Given a set of rules and a list of packets, your code decides what happens to 
each packet and returns the decisions as JSON.

You get the packet and rule data, and the one method we call. Everything else is yours to design and name.


## Rules

```json
[
  { "id": "R1", "action": "DENY",  "field": "protocol",        "value": "UDP" },
  { "id": "R2", "action": "ALLOW", "field": "destinationPort", "value": "443" },
  { "id": "R3", "action": "ALLOW", "field": "sourceIp",        "value": "10.0.0.5" },
  { "id": "R4", "action": "DENY",  "field": "destinationPort", "value": "22" }
]
```

## Packets

```json
[
  { "sourceIp": "10.0.0.5",    "destinationIp": "93.184.216.34", "destinationPort": 443,  "protocol": "TCP" },
  { "sourceIp": "10.0.0.5",    "destinationIp": "8.8.8.8",       "destinationPort": 53,   "protocol": "UDP" },
  { "sourceIp": "192.168.1.9", "destinationIp": "10.0.0.1",      "destinationPort": 22,   "protocol": "TCP" },
  { "sourceIp": "172.16.0.4",  "destinationIp": "10.0.0.1",      "destinationPort": 8080, "protocol": "TCP" },
  { "sourceIp": "10.0.0.5",    "destinationIp": "10.0.0.1",      "destinationPort": 8080, "protocol": "TCP" }
]
```
