create database Wizard;
use Wizard;

CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(150) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('Admin', 'Property Manager', 'Tenant') NOT NULL,
    DateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLogin DATETIME
);

CREATE TABLE Property (
    PropertyID INT AUTO_INCREMENT PRIMARY KEY,
    ManagerID INT NOT NULL,
    Address VARCHAR(255) NOT NULL,
    Description TEXT,
    Rent DECIMAL(10, 2) NOT NULL,
    Status ENUM('Available', 'Rented', 'Pending Approval') NOT NULL,
    DateListed DATETIME DEFAULT CURRENT_TIMESTAMP,
    DateUpdated DATETIME ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ManagerID) REFERENCES User(UserID) ON DELETE CASCADE
);

CREATE TABLE RentalAgreement (
    AgreementID INT AUTO_INCREMENT PRIMARY KEY,
    PropertyID INT NOT NULL,
    TenantID INT NOT NULL,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    MonthlyRent DECIMAL(10, 2) NOT NULL,
    AgreementStatus ENUM('Active', 'Terminated', 'Pending') NOT NULL,
    DateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PropertyID) REFERENCES Property(PropertyID) ON DELETE CASCADE,
    FOREIGN KEY (TenantID) REFERENCES User(UserID) ON DELETE CASCADE
);

CREATE TABLE Message (
    MessageID INT AUTO_INCREMENT PRIMARY KEY,
    SenderID INT NOT NULL,
    ReceiverID INT NOT NULL,
    Content TEXT NOT NULL,
    DateSent DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Sent', 'Read', 'Deleted') DEFAULT 'Sent',
    FOREIGN KEY (SenderID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ReceiverID) REFERENCES User(UserID) ON DELETE CASCADE
);

CREATE TABLE SystemSettings (
    SettingID INT AUTO_INCREMENT PRIMARY KEY,
    Key VARCHAR(100) NOT NULL UNIQUE,
    Value TEXT NOT NULL,
    DateUpdated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE RentalApplication (
    ApplicationID INT AUTO_INCREMENT PRIMARY KEY,
    TenantID INT NOT NULL,
    PropertyID INT NOT NULL,
    ApplicationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    FOREIGN KEY (TenantID) REFERENCES User(UserID) ON DELETE CASCADE,
    FOREIGN KEY (PropertyID) REFERENCES Property(PropertyID) ON DELETE CASCADE
);

CREATE TABLE Profile (
    ProfileID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    RentalHistory TEXT,
    DateUpdated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES User(UserID) ON DELETE CASCADE
);
