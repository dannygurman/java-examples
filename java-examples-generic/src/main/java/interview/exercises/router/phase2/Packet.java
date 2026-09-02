package interview.exercises.router.phase2;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
