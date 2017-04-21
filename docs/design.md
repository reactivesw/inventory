# Inventory Design

## 1. Basic Features

## 2. Model Design

## 3. Workflow

## 4. Event Design

### 4.1 Event Consumer

#### 4.1.1 Model

#### 4.1.2 Subscription

* Topic name: `reactivesw-order-created`
* Subscription name: `inventory-order-created`

Use gcloud command to create the subscription:

```bash
gcloud beta pubsub subscriptions create --topic reactivesw-order-created inventory-order-created
```

#### 4.1.3 Workflow

1. get list of InventoryRequest and orderId from event message
2. validate orderId: if orderId is exist, acknowledge to blocker, stop this process
3. remove inventory: remove sku inventory in one transaction, acknowledge to blocker
4. store orderId: if remove inventory successful, save orderId
5. store remove inventory event

### 4.2 Event Producer

#### 4.2.1 Model

* event data

| field name | field type | comment |
|---|---|---|
| orderId | String | |
| result | Boolean  | true when remove inventory successful |
| message | String | |

#### 4.2.2 Topic

Topic name: `reactivesw-inventory-reserved`

Use gcloud command to create the topic:

```bash
gcloud beta pubsub topics create reactivesw-inventory-reserved
```

#### 4.2.3 Workflow

1. get list of event, when it's `Created` status or `Pending` status but created 1 minutes ago
2. convert list of event to event message
3. publish message
4. delete event