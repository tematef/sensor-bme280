package core;

import data_objects.Telemetry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static core.CsvProcessor.TableHeaders.ID;
import static core.CsvProcessor.TableHeaders.HUMIDITY;
import static core.CsvProcessor.TableHeaders.MILLIS;
import static core.CsvProcessor.TableHeaders.TEMPERATURE;
import static core.CsvProcessor.TableHeaders.PRESSURE;

public class CsvProcessor {

    private String path;

    public CsvProcessor(File file) {
        this.path = file.getPath();
    }

    public List<Telemetry> readResultFile() {
        List<Telemetry> telemetryList = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withHeader(ID.name(), MILLIS.name(), TEMPERATURE.name(), HUMIDITY.name(), PRESSURE.name())
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.getRecordNumber() == 1) continue;
                Telemetry telemetry = new Telemetry()
                        .setId(Long.valueOf(csvRecord.get(ID)))
                        .setMillis(Long.valueOf(csvRecord.get(MILLIS)))
                        .setTemperature(Double.valueOf(csvRecord.get(TEMPERATURE)))
                        .setHumidity(Double.valueOf(csvRecord.get(HUMIDITY)))
                        .setPressure(Double.valueOf(csvRecord.get(PRESSURE)));
                telemetryList.add(telemetry);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error occured while reading results file");
        }
        return telemetryList;
    }

    public void writeResults(Telemetry tel) {
        try (
                FileWriter writer = new FileWriter(path, true);
        ) {
            writer.append(String.valueOf(tel.getId()))
                    .append(",")
                    .append(String.valueOf(tel.getMillis()))
                    .append(",")
                    .append(String.valueOf(tel.getTemperature()))
                    .append(",")
                    .append(String.valueOf(tel.getHumidity()))
                    .append(",")
                    .append(String.valueOf(tel.getPressure()))
                    .append("\n")
                    .flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error occured while writing results into file");
        }
    }

    public CsvProcessor addHeadersToFile() {
        try (
                FileWriter writer = new FileWriter(path);
        ) {
            writer.append(ID.name())
                    .append(",")
                    .append(MILLIS.name())
                    .append(",")
                    .append(TEMPERATURE.name())
                    .append(",")
                    .append(HUMIDITY.name())
                    .append(",")
                    .append(PRESSURE.name())
                    .append("\n")
                    .flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error occured while writing headers into file");
        }
        return this;
    }
    public enum TableHeaders {
        ID,
        MILLIS,
        TEMPERATURE,
        HUMIDITY,
        PRESSURE;
    }
}