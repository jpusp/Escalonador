package data;

import process.BCP;

import java.util.List;

public interface ProcessRepository {

    List<BCP> loadProcesses();
}
