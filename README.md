# dvla-selenium

**This framework attempts the following tasks:**

**Task 1:**

- Read from specified directory a list of file with different format
- For each file, get "filename", "mimetype", "size", "file extension"

**Task 2:**

- Read a registration file that consists of vehicle registration numbers
- Run automated test on https://www.gov.uk/get-vehicle-information-from-dvla
- Get "Model" and "Color" for each registration number
- Write data to a ".csv" file

**Technologies Used**
- Java
- Maven
- Selenium (Chrome Driver)
- Mockito
- log4j for Loggin info
- Tika to get file "mime type"