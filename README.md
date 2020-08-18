# hexy

A simple program like `hexdump`. This was written entirley as a learning project and is not suitable for real use.

## Usage

```
$ java -jar hexy-0.1.0-standalone.jar [filepath]
```

Output should be equivalent to `hexdump [filepath]`, and for the most part it is. However, on larger files there seems to be a bug. When running hexy on its own 4.4mb standalone jar there were slight differences between the output and that of `hexdump`.

## Options

No options are supported at this time.

## Examples

```
$ java -jar target/uberjar/hexy-0.1.0-SNAPSHOT-standalone.jar ./hello.txt
0000000 48 65 79 20 74 68 65 72 65 2c 0a 74 68 69 73 20
0000010 69 73 20 6a 75 73 74 20 61 20 74 65 73 74 20 66
0000020 69 6c 65 20 66 6f 72 20 75 73 65 20 77 69 74 68
0000030 20 68 65 78 79 0a 28 61 6e 64 20 68 65 78 64 75
0000040 6d 70 29 0a
0000044
```

### Bugs

Larger files seem to produce differences in output from `hexdump`.

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2020 Ian Sinnott

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
