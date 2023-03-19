package es.jfpastor.app.service.export;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ExportFactory {
    private final Map<ExportType, IExport<?>> exportMap;

    public ExportFactory(List<IExport<?>> exports) {
        exportMap = exports.stream().collect(Collectors.toUnmodifiableMap(IExport::getType, Function.identity()));
    }

    public IExport<?> getExport(ExportType exportType) {
        return Optional.ofNullable(exportMap.get(exportType)).orElseThrow(IllegalArgumentException::new);
    }
}
