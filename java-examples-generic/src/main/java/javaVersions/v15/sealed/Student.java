package javaVersions.v15.sealed;

import lombok.Data;

@Data
public sealed class Student extends Person permits ComputerStudent {

    private int studentId;
}
