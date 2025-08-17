PackageBuilder is deprecated and removed in modern Drools versions.
the PackageBuilder class is not available in recent versions of Drools (7.x or later).
It was part of the Drools 5.x and early 6.x API and has since been deprecated and removed.

Migrate to the KIE API (KieServices, KieSession, etc.).

Only use PackageBuilder with very old Drools versions (like 5.x/6.0.x), and only if you must.

Old vs New
-----------------------------------------------------------------------
| Old API (Deprecated) | New KIE API                                  |
| -------------------- | -------------------------------------------- |
| `PackageBuilder`     | `KieServices`, `KieFileSystem`, `KieBuilder` |
| `RuleBase`           | `KieContainer`                               |
| `WorkingMemory`      | `KieSession`                                 |
-----------------------------------------------------------------------