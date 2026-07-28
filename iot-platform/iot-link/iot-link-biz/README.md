# X509Util

## ❓Introduction

This is a java util class mainly with some methods to generate `X509V3Certificate` and `X509v2CRL`.

There are three ways to generate certificates in the utility class, using the following three different JCE API:

1. `X509V3CertificateGenerator`（Deprecated, but still valid）
2. `X509v3CertificateBuilder`（Not recommended, because its DN uses X500Name）
3. `JcaX509v3CertificateBuilder`（extended X509v3CertificateBuilder, is the best choice）

There are a number of other helper methods in the utility class, with detailed explanations in the comments.
