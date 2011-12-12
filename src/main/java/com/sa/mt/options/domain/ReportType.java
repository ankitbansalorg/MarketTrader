package com.sa.mt.options.domain;

public enum ReportType {
    WEEKLY("Weekly"), MONTHLY("Monthly"), YEARLY("Yearly"), QUARTERLY("Quarterly");

    private String reportType;

    ReportType(String reportType) {
        this.reportType = reportType;
    }

    public static ReportType identify(String reportTypeString) {
        for(ReportType reportType : ReportType.values()) {
            if(reportType.reportType.equals(reportTypeString)) {
                return reportType;
            }
        }
        return null;
    }
}
