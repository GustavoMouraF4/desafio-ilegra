package br.com.ilegra.gustavomoura.desafio.service;

import br.com.ilegra.gustavomoura.desafio.exception.FileProcessException;
import org.junit.Assert;
import org.junit.Test;

public class ProcessFileTest {

    @Test
    public void ProcessLine() throws FileProcessException {
        ProcessFile processFile = new ProcessFile();
        processFile.processLine("002ç2345675434544345çJose da SilvaçRural");
        Assert.assertTrue(processFile.getQuantityOfCustomers() != 0);
    }

}