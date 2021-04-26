##Evaluation Criteria##

• Clean Code
• Simplicity
• Logic
• SOC (Separation of Concerns)
• Flexibility/Extensibility
• Scalability/Performance

##Statement of Work##

The system must be able to import lots of flat files, read and analyse the data, and output a report.
Please read the following for more information about the input files, data analysis and the needed
output.

Flat file layout

There are 3 kinds of data inside those files. For each kind of data there is a different layout.
Salesman data has the format id 001 and the line will have the following format:

• 001çCPFçNameçSalary

Customer data has the format id 002 and the line will have the following format:

• 002çCNPJçNameçBusiness Area

Sales data has the format id 003. Inside the sales row, there is the list of items, which is
wrapped by square brackets []. The line will have the following format:

• 003çSale IDç[Item ID-Item Quantity-Item Price]çSalesman name

##Data analysis##

Your system must read data from the default directory, located at %HOMEPATH%/data/in. The system must only read .dat files.
After processing all files inside the input default directory, the system must create a flat file inside
the default output directory, located at %HOMEPATH%/data/out. The filename must follow this
pattern:

{flat_file_name}.done.dat

The output file contents should summarize the following data:

• Amount of clients in the input file
• Amount of salesman in the input file
• ID of the most expensive sale
• Worst salesman ever

This application should be running all the time, without any breaks. Everytime new files become
available, everything should be executed.