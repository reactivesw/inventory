package io.reactivesw.inventory.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.reactivesw.inventory.application.model.action.AddQuantityAction;
import io.reactivesw.inventory.application.model.action.AddReservedQuantityAction;
import io.reactivesw.inventory.application.model.action.RemoveQuantityAction;
import io.reactivesw.inventory.application.model.action.RemoveReservedQuantityAction;
import io.reactivesw.inventory.application.model.action.SetExpectedDelivery;
import io.reactivesw.inventory.application.model.action.SetQuantityAction;
import io.reactivesw.inventory.application.model.action.SetRestockableInDays;
import io.reactivesw.inventory.application.model.action.SetSupplyChannel;

/**
 * configurations for common update actions that will be used in more thant one service
 * and this action also extends other action configure in each service.
 * Created by umasuo on 16/11/21.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = AddQuantityAction.class, name = "addQuantity"),
    @JsonSubTypes.Type(value = RemoveQuantityAction.class, name = "removeQuantity"),
    @JsonSubTypes.Type(value = SetQuantityAction.class, name = "setQuantity"),
    @JsonSubTypes.Type(value = SetRestockableInDays.class, name = "setRestockableInDays"),
    @JsonSubTypes.Type(value = SetExpectedDelivery.class, name = "setExpectedDelivery"),
    @JsonSubTypes.Type(value = SetSupplyChannel.class, name = "setSupplyChannel"),
    @JsonSubTypes.Type(value = RemoveReservedQuantityAction.class, name = "removeReserved"),
    @JsonSubTypes.Type(value = AddReservedQuantityAction.class, name = "addReserved"),
})
public interface UpdateAction {
  String getActionName();
}
