package br.com.ilegra.gustavomoura.desafio.service;

import br.com.ilegra.gustavomoura.desafio.exception.FileProcessException;
import br.com.ilegra.gustavomoura.desafio.exception.FileReadException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class DataService {

    private String PATH = "/home/ilegra/data/in";
    private ProcessFile processFile = new ProcessFile();
    private CreateOutPut createOutPut = new CreateOutPut();

    public void dataAnalysingRun(){
        try(WatchService service = FileSystems.getDefault().newWatchService()) {
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get(PATH);
            keyMap.put(path.register(service, StandardWatchEventKinds.ENTRY_CREATE), path);

            WatchKey watchKey;
            do {
                watchKey = service.take();
                Path eventDir = keyMap.get(watchKey);

                for (WatchEvent<?> event : watchKey.pollEvents()){
                    Path eventPath = (Path) event.context();
                    File file = new File(eventDir + "/" + eventPath);
                    if (file.getName().endsWith(".dat")){
                        processFile.readAndProcessFile(file);
                        createOutPut.createOutputFile();
                    }
                }
            }while (watchKey.reset());
        } catch (IOException | InterruptedException | FileReadException | FileProcessException e) {
            e.printStackTrace();
        }
    }

}
