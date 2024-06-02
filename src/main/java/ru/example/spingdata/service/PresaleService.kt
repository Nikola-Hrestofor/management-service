package ru.example.spingdata.service

import lombok.extern.slf4j.Slf4j
import org.camunda.bpm.engine.RuntimeService
import org.springframework.stereotype.Service
import ru.example.spingdata.dto.PresaleItem
import ru.example.spingdata.dto.PresaleRequest
import ru.example.spingdata.entity.PresaleEntity
import ru.example.spingdata.repository.PresaleRepository
import java.util.*
import java.util.logging.Logger

@Slf4j
@Service
class PresaleService(
    private val presaleRepository: PresaleRepository,
    private val runtimeService: RuntimeService,
) {
    companion object {
        val logger = Logger.getLogger(PresaleService::class.java.name)
    }

    fun startPresale(request: PresaleRequest) {
        val uuidNote = createPresaleNote(request.items)
        logger.info { "uuidNote $uuidNote" }

        startBpmn(uuidNote, request.count, request.name)
    }

    private fun startBpmn(uuid: UUID, count: Long, name: String) {
        runtimeService.startProcessInstanceByKey("presale", mutableMapOf(
            Pair("uuid", uuid),
            Pair("count", count),
            Pair("name", name),
            ) as Map<String, Any>?
        )

    }

    private fun createPresaleNote(items: List<PresaleItem>): UUID {
        val uuid = UUID.randomUUID()

        presaleRepository.saveAll(
            items
            .map { PresaleEntity(null, uuid, it.id, it.type.name, it.qty) }
        )

        return uuid
    }
}