package task3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class task3 {

    static class Value {

        private int id;

        private String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    static class Test {

        private int id;

        private String value;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {

            System.err.println("Ошибка: java task3 <values.json> <tests.json> <report.json>");

            System.exit(1);
        }

        String valuesFilePath = args[0];

        String testsFilePath = args[1];

        String reportFilePath = args[2];

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            List<Value> values = objectMapper.readValue(new File(valuesFilePath), new TypeReference<List<Value>>() {});

            List<Test> tests = objectMapper.readValue(new File(testsFilePath), new TypeReference<List<Test>>() {});

            for (Test test : tests) {

                int testId = test.getId();

                for (Value value : values) {

                    if (value.getId() == testId) {

                        test.setValue(value.getValue());

                        break;
                    }
                }
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), tests);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
