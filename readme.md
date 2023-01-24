# Fatefall - Card Editor

An application for creating, editing, and organizing collections of Cards.

Can be run as a standalone JavaFX application or accessed in a web browser via [JPro](https://www.jpro.one/docs/current/1.8/JPRO%20CHECKLIST).

## Data Model

### Workspace
A Workspace is a collection of Cards.

### Card
A Card is the core entity. A card has two faces (front and back) with matching width and height.

### Attribute
An Attribute is a key-value pair that can belong to any other entity (Workspace, Card, and even Attribute). This allows for unstructured hierarchical data.

## Plugins
Plugin based architecture allows for providing custom features.

### License
To the extent possible under law, the author has waived all copyright and related or neighboring rights to this work.