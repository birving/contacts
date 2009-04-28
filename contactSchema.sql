/* 
    Schema for the Contact database. 
## Tables -
	associationType
	communication
	company
	companyAssociation
	locator
	medium
	person
	person_communication
	position

#### OBSOLETE - 
	companyType
/*

/* Table 'associationType' represents the types of relationships between two entities (companies). e.g. Parent-Subsidiary, Recruiter-Employer */
CREATE
    TABLE associationType
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        name VARCHAR(50) NOT NULL,
        description VARCHAR(100),
        PRIMARY KEY (associationTypeID),
        INDEX name_index (name)
    )

/* Table 'Communication' represents instances of contact between two or more people over a specific medium (email, phone, face-to-face, etc).
   This relies on the association table 'person_communication' to establish the many-to-many association.
   The database *could* allow am implied participant: me, and one or more other participants; or it could require all participants to be included.
   -- Should this also include an optional link to the position being discussed?
   -- What if there are two jabs discussed?
*/
CREATE
    TABLE communication
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        mediumID INT,
        initiatorPersonID INT,
        time DATETIME,
        content TEXT,
        notes VARCHAR(1000),
        PRIMARY KEY (communicationID)
    )

/* Table 'Company' - Company Information */ 
CREATE
    TABLE company
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        -- companyTypeID INT NOT NULL, (OBSOLETE)
        -- parentCompanyID INT, (OBSOLETE)
        name VARCHAR(100) NOT NULL,
        webPage VARCHAR(100),
        notes VARCHAR(1000),
        PRIMARY KEY (companyID),
        INDEX name_index (name)
    )

/* 
Table 'companyAssociation' represenst the association between two companies. Association could be parent-subsidiary, employer-recruiter, etc.
*/
CREATE
    TABLE companyAssociation
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        company1ID INT NOT NULL,
        company2ID INT NOT NULL,
        associationTypeID INT NOT NULL,
        notes VARCHAR(1000),
        PRIMARY KEY (id),
        CONSTRAINT company_index UNIQUE (company1ID, company2ID),
        CONSTRAINT reverse_index UNIQUE (company2ID, company1ID)
    )

/* Locator stores a person's email addresses, phone numbers, etc. */
CREATE
    TABLE locator
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        personID INT NOT NULL,
        mediumID INT NOT NULL,
        value VARCHAR(100) NOT NULL,
        notes VARCHAR(1000),
        PRIMARY KEY (locatorID),
        INDEX person_index (personID, mediumID)
    )

/* Medium is a means of communication; e.g email, phone */
CREATE
    TABLE medium
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        type VARCHAR(20) NOT NULL,
        name VARCHAR(20) NOT NULL,
        notes VARCHAR(1000),
        PRIMARY KEY (mediumID),
        UNIQUE INDEX name_index (name)
    )

/* Person represents a person with whom we may have communications */
CREATE
    TABLE person
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        companyID INT,
        lastName VARCHAR(50) NOT NULL,
        firstName VARCHAR(50) NOT NULL,
        position VARCHAR(100),
        notes VARCHAR(1000),
        PRIMARY KEY (personID),
        INDEX name_index (lastName, firstName),
        INDEX company_index (companyID)
    )

/* Table 'person_communication' represents the many-to-many association between people and communications */
CREATE
    TABLE person_communication
    (
        --id INT NOT NULL,
        --version INT NOT NULL,
        --created INT NOT NULL,
        --updated INT NOT NULL,
        personID INT NOT NULL,
        communicationID INT NOT NULL,
        PRIMARY KEY (personID, communicationID),
        CONSTRAINT communication_index UNIQUE (communicationID, personID)
    )

/* Table 'position' is a possible job at a company 
   -- Could add a recruiterPersonID??
*/
CREATE
    TABLE position
    (
        id INT NOT NULL,
        version INT NOT NULL,
        created INT NOT NULL,
        updated INT NOT NULL,
        companyID INT NOT NULL,
        recruiterCompanyID INT,
        identifier VARCHAR(100),
        status VARCHAR(100),
        description VARCHAR(1000),
        PRIMARY KEY (positionID)
    )

-- Views
CREATE VIEW v_person as 
SELECT p.firstName, p.lastName, p.position, c.name as company, c.webpage, m.name as medium, l.value, p.notes
FROM company c
    right outer join person p on p.companyID = c.companyID 
    left outer join locator l on p.personID = l.personID
    left outer join medium m on l.mediumID = m.mediumID
order by p.lastName, p.firstname, m.type, m.name


