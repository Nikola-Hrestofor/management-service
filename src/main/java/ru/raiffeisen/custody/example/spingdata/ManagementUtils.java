package ru.raiffeisen.custody.example.spingdata;

import camundajar.impl.scala.collection.convert.impl.IteratorStepperBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;
import ru.raiffeisen.custody.example.spingdata.dto.ComponentDto;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ManagementUtils {

    private final ObjectMapper objectMapper;
    public <T> T getVariable(String key, Class<T> clazz, DelegateExecution execution){
        return Optional.ofNullable(execution.getVariable(key))
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .orElse(null);
    }

    @SneakyThrows
    public void setObject(String key, Object o, DelegateExecution execution){
        setVariable(key, objectMapper.writeValueAsString(o), execution);
    }

    public void setVariable(String key, Object o, DelegateExecution execution) {
        execution.setVariable(key, o);
    }

    @SneakyThrows
    private <T> T readUnchecked(String json, Class<T> clazz){
        return objectMapper.readValue(json, clazz);
    }

    public <T> T getObject(String key, Class<T> clazz, DelegateExecution execution) {
        return Optional.ofNullable(execution.getVariable(key))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(o -> readUnchecked(o, clazz))
                .orElse(null);
    }

    public <T> List<T> getList(String key, Class<T> clazz, DelegateExecution execution) {
        return Optional.ofNullable(execution.getVariable(key))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(s -> readUnchecked(s, LinkedList.class, clazz))
                .orElse(null);
    }

    public List<ComponentDto> getListComponents(String json) {
        return Optional.ofNullable(json)
                .map(s -> readUnchecked(s, LinkedList.class, ComponentDto.class))
                .orElse(null);
    }

    @SneakyThrows
    private <T, C extends Collection<T>> List<T> readUnchecked(String json, Class<C> col, Class<T> el){
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(col, el);
        return objectMapper.readValue(json, listType);
    }
}
