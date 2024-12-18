package org.example.bugreporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.logging.Logger;
import java.util.Map;

import org.example.classfinder.ClassFinder;

/**
 * This class scans the package lesson10.labs.prob2.javapackage
 * for classes with annotation @BugReport. It then generates a bug report
 * bugreport.txt, formatted like this:
 *
 * Joe Smith
 *     reportedBy:
 *     classname:
 *     description:
 *     severity:
 *
 *     reportedBy:
 *     classname:
 *     description:
 *     severity:
 *
 * Tom Jones
 *     reportedBy:
 *     classname:
 *     description:
 *     severity:
 *
 *     reportedBy:
 *     classname:
 *     description:
 *     severity:
 *
 *
 */

public class BugReportGenerator {
	private static final Logger LOG = Logger.getLogger(BugReportGenerator.class.getName());
	private static final String PACKAGE_TO_SCAN = "org.example.javapackage";
	private static final String REPORT_NAME = "bug_report.txt";
	private static final String REPORTED_BY = "reportedBy: ";
	private static final String CLASS_NAME = "classname: ";
	private static final String DESCRIPTION = "description: ";
	private static final String SEVERITY = "severity: ";

	public void reportGenerator() {
		List<Class<?>> classes = ClassFinder.find(PACKAGE_TO_SCAN);

		Map<String, List<BugReport>> bugs = new HashMap<>();

		for (Class<?> cl : classes) {
			if (cl.isAnnotationPresent(BugReport.class)) {
				BugReport annotation = (BugReport) cl.getAnnotation(BugReport.class);

				String name = annotation.assignedTo();

				List<BugReport> assignedBugs = bugs.getOrDefault(name, new ArrayList<>());
				assignedBugs.add(annotation);
				bugs.put(name, assignedBugs);
			}
		}

		// Write the report to the file
		try (FileWriter fileWriter = new FileWriter(REPORT_NAME);
			 PrintWriter writer = new PrintWriter(fileWriter)) {

			for (Map.Entry<String, List<BugReport>> entry : bugs.entrySet()) {
				String developer = entry.getKey();
				List<BugReport> assignedBugs = entry.getValue();


				writer.println(developer);

				// Write details of each bug
				for (BugReport bug : assignedBugs) {

					for (Class<?> cl : classes) {
						if (cl.isAnnotationPresent(BugReport.class)) {
							BugReport annotation = (BugReport) cl.getAnnotation(BugReport.class);

							if (annotation.equals(bug)) {
								writer.println("\t" + REPORTED_BY + bug.reportedBy());
								writer.println("\t" + CLASS_NAME + cl.getName());
								writer.println("\t" + DESCRIPTION + bug.description());
								writer.println("\t" + SEVERITY + bug.severity());
								writer.println();
							}
						}
					}
				}
			}

		} catch (IOException e) {
			LOG.severe("Error writing bug report: " + e.getMessage());
		}
	}
}
