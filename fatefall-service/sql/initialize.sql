CREATE TABLE entities (
id 							varchar(100)	not null,
type 						varchar(100) 	not null,
created_timestamp 			bigint 			not null,
updated_timestamp			bigint,

PRIMARY KEY (id)
);

CREATE TABLE workspaces (
id 							varchar(100) 	not null,
name 						varchar(255) 	not null,
created_timestamp 			bigint	 		not null,
updated_timestamp 			bigint,

PRIMARY KEY (id),
FOREIGN KEY (id) 			REFERENCES entities(id)
);

CREATE TABLE cards (
id 							varchar(100) 	not null,
name 						varchar(255) 	not null,
template_id					varchar(255),
created_timestamp 			bigint 			not null,
updated_timestamp 			bigint,

PRIMARY KEY (id),
FOREIGN KEY (id) 			REFERENCES entities(id)
);

CREATE TABLE fields (
id 							varchar(100) 	not null,
parent_id 					varchar(100)	not null,
name 						varchar(255) 	not null,
created_timestamp			bigint 			not null,
updated_timestamp 			bigint,

PRIMARY KEY (id),
FOREIGN KEY (id) 			REFERENCES entities(id)
);


CREATE TABLE relation_workspace_to_card (
workspace_id 				varchar(100) 	not null,
card_id 					varchar(255) 	not null,

FOREIGN KEY (workspace_id)	REFERENCES workspaces(id),
FOREIGN KEY (card_id) 		REFERENCES cards(id)
);