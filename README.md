# bench[marks]

```bash
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ScodecEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JsoniterEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ChillEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.PicklingEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JavaEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.JacksonEncode1.*
jmh:run -i 10 -wi 10 -f1 -t1 .*binary.ProtobufEncode1.*
```
