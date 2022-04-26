# jmh-json

```bash
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ScodecEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ScodecDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JsoniterEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JsoniterDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ChillEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ChillDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.PicklingEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.PicklingDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JavaEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JavaDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JacksonEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JacksonDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ProtobufEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ProtobufDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ArgonautEncode.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ArgonautDecode.*

jmh:run -i 10 -wi 10 -f1 -t1 .*TestTry.*

jmh:run -i 10 -wi 10 -f1 -t1 .*JsonDecode*
```

## results

```
Benchmark                 Mode  Cnt        Score        Error  Units
JsonDecode.jsoniter      thrpt   10  7450532.835 ± 237579.075  ops/s
JsonDecode.jsonpPointer  thrpt   10   118922.144 ±    905.658  ops/s
JsonDecode.jsonpStream   thrpt   10   120648.815 ±   1741.023  ops/s
JsonDecode.nashorn       thrpt   10    10203.088 ±     64.012  ops/s
```
