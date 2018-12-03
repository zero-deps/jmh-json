# bench[marks]

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

```
