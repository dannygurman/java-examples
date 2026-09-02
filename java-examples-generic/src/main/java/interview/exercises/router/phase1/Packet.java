package interview.exercises.router.phase1;

public record Packet(String sourceIp,
                     String destinationIp,
                     int destinationPort,
                     Protocol protocol) {
}
