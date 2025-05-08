# UL Payroll System

The UL Payroll System is a Java-based application developed to automate and manage the payroll processing of staff at the University of Limerick. It supports both full-time and hourly-paid employees and adheres to UL's salary scales and pay claim processes.

Features:
  -Generates monthly payslips for staff based on current salary scales and pay claims.
  -Calculates net pay after statutory deductions (PRSI, USC, Income Tax) and other deductions (Health Insurance, Union Fees).
  -Supports salary progression and promotion logic for full-time employees.
  -Handles three user roles via a Command Line Interface (CLI):
    Employee: View personal details and payslip history.
    Admin: Add new employees to the system.
    HR: Manage staff promotions with confirmation workflow.
  -All data (employee records, salary scales, payslips, etc.) is stored in CSV format.
  -Designed with a modular architecture to support future GUI integration.


System Requirements:
  -Java Development Kit (JDK) 11 or higher
  -A terminal/console to run the CLI

Running the Application:
  -Compile the Java files using javac.
  -Run the main program via the CLI.
  -Use the CLI interface to interact with the system based on your user role.
  
Documentation:
  -Full documentation generated via javadoc is included.
  -UML diagrams and CRC cards are available in the docs/ folder.
  -Sample data and CSV files are located in the directory.
