# Fatefall - Card Editor

An application for creating, editing, and organizing collections of Cards.

## Data Model

### Workspace
A Workspace is a collection of Cards.

### Card
A Card is the core entity. A card has two faces (front and back) with matching width and height.

### Attribute
An Attribute is a key-value pair that can belong to any other entity (Workspace, Card, and even Attribute). This allows for unstructured hierarchical data.

## Plugins
Plugin based architecture allows for providing custom features. 

## //TODO
- [ ] Style
- [ ] Data Model Operations
  - [ ] Workspace
    - [X] Create
    - [X] Read
    - [X] Update
    - [ ] Delete
  - [ ] Template
    - [ ] Create
    - [ ] Read
    - [ ] Update
    - [ ] Delete
  - [ ] Card
    - [ ] Create
    - [X] Read
    - [ ] Update
    - [X] Delete
  - [ ] Attribute
    - [ ] Create
    - [X] Read
    - [X] Update
    - [ ] Delete

- [ ] Card Editor
  - [X] Import from MSE
    - [X] As Front/Back Image
    - [ ] As Editable Elements

- [ ] Workspace View
  - [ ] Header / Attributes
  - [ ] Swappable Skins
    - [X] Tree Table
    - [ ] Grid

- [X] Card View
  - [X] Attributes
  - [X] Swappable Skins
    - [X] Flippable
    - [X] Stacked
    - [X] Adjacent

- [ ] Plugin Management

- [ ] Multi-Users
  - [ ] Users
  - [ ] Tenants
  - [ ] Sessions

- [ ] CI/CD
  - [ ] Setup automated builds
  - [ ] Testing

- [ ] Docker


### Setup
Maven installation of `fatefall-app` uses jarsign which requires a keystore:
```
$ mkdir keystore
$ cd keystore
$ keytool -genkey -alias server -keyalg RSA -keypass password -storepass password -keystore keystore.jks
$ keytool -export -alias server -storepass password -file server.cer -keystore keystore.jks
```


### License
To the extent possible under law, the author has waived all copyright and related or neighboring rights to this work.