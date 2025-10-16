package huynhquanghuy;

import java.util.logging.Logger;

interface Printer {
    void print(String message);
}

class ConsolePrinter implements Printer {
    private static final Logger LOGGER = Logger.getLogger(ConsolePrinter.class.getName());

    @Override
    public void print(String message) {
        LOGGER.info(message);
    }
}

class Report {
    private final Printer printer;

    // Dependency Injection via constructor
    public Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }

    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        Report report = new Report(printer);
        report.generate();
    }
}
