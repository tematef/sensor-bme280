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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
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
