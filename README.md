# Project Information

This project provides a user interface to interact with an automated price-tracking web scraper. Currently, the tracker scrapes amazon.com but could add multiple sites later.
Users can add/remove a product to be tracked, where each hour the application will perform a web scrapping on all the products where tracking is enabled.
Users can visualize the price variation on a graph from when the product was added to the date where the last scraping happened.

## Libraries/Frameworks/Modules

This project uses:

- React
- Spring Boot
- Playwright

## Spring Boot Backend

To get started with this Spring Boot application, follow these steps:

1. **Navigate to the Backend Directory**:

   Navigate to the Backend Directory using the following command:

   ```bash
   cd College/backend
   
2. **Configure the Database**:

   a. Create a Database Webscrapping.

   b. Open the application.properties file located in the src/main/resources directory and add your Microsoft SQL Server credentials (username and password):
      - spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=Webscrapping;trustServerCertificate=true;
      - spring.datasource.username=your_username
      - spring.datasource.password=your_password

3. **Build and Run the Application**:

   Use Maven to build and run the application:

   ```bash
   mvn spring-boot:run


### Running the React Frontend

- `cd frontend`
- `npm i`
- `npm run start`


