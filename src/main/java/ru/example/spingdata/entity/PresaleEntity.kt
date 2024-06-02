package ru.example.spingdata.entity

import ru.example.spingdata.api.dto.enums.UnitType
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "presale_card", schema = "management")
class PresaleEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = 0,

    var uuid: UUID = UUID.randomUUID(),

    @Column(name = "item_id")
    var itemId: Long = 0,

    var type: String = UnitType.COMPONENT.name,

    var qty: BigDecimal = BigDecimal(0)
) {
    override fun toString(): String {
        return "PresaleEntity(id=$id, uuid=$uuid, itemId=$itemId, type='$type', qty=$qty)"
    }
}