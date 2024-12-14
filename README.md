![Alt text](relative%20path/to/img.jpg?raw=true "Logo")

# EcoMeter

## I. Project Overview

EcoMeter is a software application designed to help households track and analyze their energy consumption. It provides insights into daily energy usage, helping users identify areas to reduce consumption and save on energy costs. With a user-friendly interface, EcoMeter empowers families to make smarter, more energy-efficient choices.

## II. Application of Object-Oriented Programming (OOP) Principles

EcoMeter was built with OOP principles to ensure scalability, modularity, and maintainability. Below are the core OOP principles applied:

- **Encapsulation:** Key functionalities, such as energy data processing and analytics, are encapsulated within classes, ensuring that data integrity is maintained and only relevant methods have access to sensitive data.
- **Inheritance:** The project employs inheritance to create specialized classes for different appliance types that inherit common functionality from a base Appliances class.
- **Polymorphism:** Methods such as `displayTip()` are overridden in derived classes to provide tailored calculations based on the tip.
- **Abstraction:** Core functionalities like data retrieval, energy usage calculation, and reporting are abstracted to simplify complex operations, allowing users to interact seamlessly with the application.

## III. Chosen Sustainable Development Goal (SDG) and Its Integration

EcoMeter aligns with **SDG 13: Climate Action**. The application supports this goal by:

- Encouraging households to reduce their energy consumption, thereby lowering their carbon footprint.
- Providing actionable insights based on household energy usage to foster sustainable practices.
- Raising awareness about the environmental impact of excessive energy consumption and promoting behavior changes to mitigate climate change.

## IV. Instructions for Running the Program

### Prerequisites
- Java (JDK 11 or higher)

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/Darcylet/EcoMeter.git
   ```
2. Navigate to the project directory:
   ```bash
   cd EcoMeter
   ```
3. Install the required dependencies:
   ```bash
   Refer to the `pom.xml` file for Maven dependencies or ensure that required libraries are included in your project setup.
   ```
4. Run the program:
   ```bash
   Run the `EcoMeter` application using your Java IDE or build the project with Maven/Gradle and execute the resulting JAR file.
   ```

### Usage
- Upon launching the application, follow the prompts to input your household's energy consumption data.
- View detailed reports and recommendations in the dashboard.


