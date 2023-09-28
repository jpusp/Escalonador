package data;

import process.BCP;
import process.ProcessState;

import java.util.ArrayList;
import java.util.List;

public class MockProcessRepositoryImpl implements ProcessRepository {
    @Override
    public List<BCP> loadProcesses() {
        return new ArrayList<>(
                List.of(
                    new BCP(
                        "TESTE-1",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                        "X=8",
                                        "COM",
                                        "COM",
                                        "COM",
                                        "E/S",
                                        "Y=10",
                                        "X=2",
                                        "COM",
                                        "E/S",
                                        "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-2",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "Y=10",
                                            "X=2",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "X=3",
                                            "X=9",
                                            "Y=1",
                                            "COM",
                                            "COM",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-3",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "Y=5",
                                            "X=2",
                                            "COM",
                                            "COM",
                                            "X=3",
                                            "X=91",
                                            "Y=10",
                                            "COM",
                                            "X=4",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-4",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "E/S",
                                            "COM",
                                            "E/S",
                                            "Y=5",
                                            "E/S",
                                            "COM",
                                            "E/S",
                                            "X=3",
                                            "X=91",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-5",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "E/S",
                                            "COM",
                                            "E/S",
                                            "Y=5",
                                            "E/S",
                                            "COM",
                                            "E/S",
                                            "COM",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-6",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "Y=5",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-7",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "Y=5",
                                            "COM",
                                            "X=10",
                                            "X=12",
                                            "Y=3",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-8",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "X=8",
                                            "COM",
                                            "COM",
                                            "Y=5",
                                            "COM",
                                            "X=10",
                                            "X=12",
                                            "Y=3",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "X=1",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-9",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    ),
                    new BCP(
                            "TESTE-10",
                            ProcessState.READY,
                            new ArrayList<>(
                                    List.of(
                                            "COM",
                                            "COM",
                                            "COM",
                                            "COM",
                                            "E/S",
                                            "SAIDA"
                                    )
                            )
                    )
                )
        );
    }
}
