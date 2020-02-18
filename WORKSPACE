workspace(name = "grpc_dagger")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

GRPC_VERSION = "1.27.1"

http_archive(
 name = "io_grpc_grpc_java",
     sha256 = "518e8389114a6b87056208981932f3559ef8a42d8af035709ad36f9140961be6",
 strip_prefix = "grpc-java-%s"%GRPC_VERSION,
 url = "https://github.com/grpc/grpc-java/archive/v%s.zip"%GRPC_VERSION,
)
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS")

http_archive(
    name = "rules_jvm_external",
    sha256 = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a",
    strip_prefix = "rules_jvm_external-3.0",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/3.0.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")

# Run grpc_java_repositories after compat_repositories to ensure the
# maven_install-selected dependencies are used.
grpc_java_repositories()

load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()


DAGGER_VERSION = "2.26"

maven_install(
    name = "dagger",
    artifacts = [
        "com.google.dagger:dagger:%s" % DAGGER_VERSION,
        "com.google.dagger:dagger-compiler:%s" % DAGGER_VERSION,
        "com.google.dagger:dagger-producers:%s" % DAGGER_VERSION,
        "com.google.dagger:dagger-grpc-server:%s" % DAGGER_VERSION,
        "com.google.dagger:dagger-grpc-server-annotations:%s" % DAGGER_VERSION,
        "com.google.dagger:dagger-grpc-server-processor:%s" % DAGGER_VERSION,
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

maven_install(
    name = "maven",
    artifacts = [
        "com.google.guava:guava:28.2-jre",
        "javax.inject:javax.inject:1",
        "javax.annotation:javax.annotation-api:1.3.2",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)