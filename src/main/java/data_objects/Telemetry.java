package data_objects;

public class Telemetry {

    private long id;
    private long millis;
    private double temperature;
    private double humidity;
    private double pressure;

    public Telemetry(long id, long millis, double temperature, double humidity, double pressure) {
        this.id = id;
        this.millis = millis;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Telemetry() {}

    public long getId() {
        return id;
    }

    public Telemetry setId(long id) {
        this.id = id;
        return this;
    }

    public long getMillis() {
        return millis;
    }

    public Telemetry setMillis(long millis) {
        this.millis = millis;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public Telemetry setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public Telemetry setHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public Telemetry setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    @Override
    public String toString() {
        return "Telemetry{" +
                "id=" + id +
                ", millis=" + millis +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
