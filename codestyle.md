# Code Style Guidelines

## General Guidelines
- Follow a consistent code style throughout the project.
- Use meaningful variable and function names.
- Write clear and concise comments where necessary.

## Spring Boot (Java)

### Formatting
- Use 4 spaces for indentation.
- Limit line length to 120 characters.
- Place each class in a separate file.
- Use camelCase for variable and method names.
- Use PascalCase for class names.

### Annotations
- Place annotations directly above the method or class they annotate.
- Group similar annotations together.

### Exception Handling
- Use specific exception types where possible.
- Provide meaningful error messages.

## Vue.js

### Formatting
- Use 2 spaces for indentation.
- Limit line length to 80 characters.
- Use kebab-case for component file names.
- Use camelCase for props and data properties.

### Template
- Use single quotes for attributes in templates.
- Keep template structure clean and organized.

### Scripts
- Use ES6 syntax (e.g., `const`, `let`, arrow functions).
- Keep methods organized within the `methods` object.
- Use `v-for` and `v-if` efficiently to avoid unnecessary re-renders.

### Styling
- Use BEM (Block Element Modifier) naming convention for CSS classes.
- Keep styles modular and scoped when using `<style scoped>`.

## Database (MySQL)

### Naming Conventions
- Use lowercase letters for table and column names.
- Separate words with underscores (e.g., `user_profiles`).
- Use singular nouns for table names (e.g., `user`, `profile`).

### Query Formatting
- Use uppercase for SQL keywords (e.g., `SELECT`, `INSERT`, `UPDATE`).
- Indent nested queries for better readability.

## Commit Messages
- Use the following format for commit messages:
