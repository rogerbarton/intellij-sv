# Ext (extends)

- Contains interfaces that are applied to BNF rules with the `implements` attribute

# Mixin

- Use mixin classes to extend functionality of BNF rules
- This is also where interfaces from `../ext` are implemented
- Classes are used/referenced in the BNF with the `mixin` attribute of a rule
    - You may have to regenerate the Parser classes in BNF for changes to update
- Classes are named `<MyRule>Mixin` and always extend:
  - `<MyRule>` interface to get access to the required members of the rule.
  - Because of this also the interfaces added to the BNF `implements` attribute
