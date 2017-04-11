# Inventory Rest API

## Introduction

TODO

## View Model

### InventoryEntryDraft

| field name | field type | comments |
|-----|------|-----|
| sku | String | required |
| quantityOnStock | Integer | required, min is 0 |
| restockableInDays | Integer | |
| expectedDelivery | ZonedDateTime | data format must be "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" |
| supplyChannel | Reference | |

### InventoryEntryView

| field name | field type | comments |
|-----|------|-----|
| id | String | |
| version | Integer | |
| createdAt | ZonedDateTime | data format is "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" |
| lastModifiedAt | ZonedDateTime | data format is "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" |
| sku | String | |
| supplyChannel | Reference | |
| quantityOnStock | Integer | |
| availableQuantity | Integer | |
| restockableInDays | Integer | |
| expectedDelivery | ZonedDateTime | data format is "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" |

### InventoryRequest

| field name | field type | comments |
|-----|------|-----|
| skuName | String | required, product sku name |
| quantity | Intege | required |

### UpdateRequest

| field name | field type | comments |
|-|-|-|
| version | Integer | required, NotNull, min is 0 |
| actions | List\<UpdateAction\> | required, NotNull |

### Update Action

#### AddQuantityAction

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `addQuantity` |
| quantity | Integer | required, Minimum value is 1 |

#### RemoveQuantityAction

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `removeQuantity` |
| quantity | Integer | required, Minimum value is 1 |

#### SetQuantityAction

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setQuantity` |
| quantity | Integer | required, Minimum value is 0 |

#### SetRestockableInDays

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setRestockableInDays` |
| restockableInDays | Integer | required, Minimum value is 1 |

#### SetExpectedDelivery

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setExpectedDelivery` |
| expectedDelivery | ZonedDateTime | |

#### SetSupplyChannel

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `setSupplyChannel` |
| supplyChannel | Reference | required, NotNull |

#### RemoveReservedQuantityAction

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `removeReserved` |
| removeReservedQuantity | Integer | required, Minimum value is 1 |

#### AddReservedQuantityAction

| field name | field type | comments |
|-|-|-|
| action | String | required, set as `addReserved` |
| addReservedQuantity | Integer | required, Minimum value is 1 |

## API

### create inventory

* URL : {service url}/
* method : POST
* request body :

  | name | type | comments |
  |-|-|-|
  | draft | InventoryEntryDraft | required |

* response : InventoryEntryView

### delete inventory

* URL : {service url}/{inventoryId}
* method : DELETE
* path variable :

  | name | type | comments |
  |-|-|-|
  | id | String | required |

* request body DeleteRequest:

  | name | type | comments |
  |-|-|-|
  | version | Integer | required |

### update inventory

* URL : {service url}/{inventoryId}
* method : PUT
* path variable :

  | name | type | comments |
  |-|-|-|
  | id | String | required |

* request body :

  | name | type | comments |
  |-|-|-|
  | updateRequest | UpdateRequest | Required |

* response : InventoryEntryView

### change sku quantity by update request

* URL : {service url}/
* method : PUT
* request body :

  | name | type | comments |
  |-|-|-|
  | requests | List\<InventoryRequest\> | required |

* response : List\<InventoryEntryView\>

### get inventory by id

* URL : {service url}/{inventoryId}
* method : GET
* path variable :

  | name | type | comments |
  |-|-|-|
  | id | String | required |

* response : InventoryEntryView

### query inventory by sku names

* URL : {service url}/
* method : GET
* request param :

  | name | type | comments |
  |-|-|-|
  | skuNames | List\<String\> | required |

* response : List\<InventoryEntryView\>