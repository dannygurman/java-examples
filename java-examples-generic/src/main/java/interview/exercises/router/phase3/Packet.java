package interview.exercises.router.phase3;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
