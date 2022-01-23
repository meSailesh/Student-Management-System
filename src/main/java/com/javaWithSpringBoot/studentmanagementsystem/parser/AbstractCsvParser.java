package com.javaWithSpringBoot.studentmanagementsystem.parser;

import com.javaWithSpringBoot.studentmanagementsystem.exception.InvalidFileFormatException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sailesh on 1/23/22.
 */
public abstract class AbstractCsvParser<T> {
    public abstract Class<? extends T> getEntity();

    public abstract Character getDelimiter();

    public List<T> parse(MultipartFile file) throws Exception {
        List<T> records = new ArrayList<T>();
        try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<?> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(getEntity())
                    .withSeparator(getDelimiter())
                    .withSkipLines(0)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            records = (List<T>)csvToBean.parse();
        } catch (Exception ex) {
           throw new InvalidFileFormatException(ex.getMessage());
        }
        return records;
    }

}
