package io.reactivesw.inventory.infrastructure.validator

import io.reactivesw.exception.ConflictException
import io.reactivesw.inventory.domain.model.InventoryEntry
import spock.lang.Specification

/**
 * Created by Davis on 17/3/28.
 */
class InventoryVersionValidatorTest extends Specification {
    InventoryVersionValidator validator = new InventoryVersionValidator()

    def "test 1 : entity's version and input version is not match and throw ConflictException"() {
        given:
        InventoryEntry entity = new InventoryEntry(version: 1)
        Integer version = 2

        when:
        InventoryVersionValidator.validate(entity, version)

        then:
        thrown(ConflictException)
    }

    def "test 2 : entity's version and input version is match and expect true"() {
        given:
        Integer version = 1
        InventoryEntry entity = new InventoryEntry(version: version)

        when:
        InventoryVersionValidator.validate(entity, version)

        then:
        true
    }
}
