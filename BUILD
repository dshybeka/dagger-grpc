load("@rules_java//java:defs.bzl", "java_binary")
load("@rules_proto//proto:defs.bzl", "proto_library")
load("@io_grpc_grpc_java//:java_grpc_library.bzl", "java_grpc_library")

proto_library(
    name = "coffee_proto",
    srcs = ["src/main/proto/coffee_service.proto"],
)

java_proto_library(
    name = "coffee_java_proto",
    deps = [":coffee_proto"],
)

java_grpc_library(
    name = "coffee_java_grpc",
    srcs = [":coffee_proto"],
    deps = [":coffee_java_proto"],
)

java_binary(
    name = "ProjectRunner",
    runtime_deps = ["app_deps", "@io_grpc_grpc_java//netty"],
    main_class = "shybeka.sample.grpc.App"
)

java_library(
    name = "app_deps",
    srcs = glob(["src/main/java/shybeka/sample/grpc/*.java"]),
    deps = ["coffee_java_proto",
            "coffee_java_grpc",
            "@com_google_protobuf//:protobuf_java",
            "@com_google_protobuf//:protobuf_java_util",
            "@io_grpc_grpc_java//api",
            "@io_grpc_grpc_java//protobuf",
            "@io_grpc_grpc_java//stub"],
)

java_binary(
    name = "DaggerGrpcRunner",
    runtime_deps = ["dagger_grpc_deps", "@io_grpc_grpc_java//netty"],
    main_class = "shybeka.sample.grpc.dagger.DaggerApp"
)

java_library(
    name = "dagger_grpc_deps",
    srcs = glob(["src/main/java/shybeka/sample/grpc/dagger/*.java"]),
    deps = ["coffee_java_proto",
            "@dagger//:com_google_dagger_dagger",
            "@dagger//:com_google_dagger_dagger_compiler",
            "@dagger//:com_google_dagger_dagger_producers",
            "@dagger//:com_google_dagger_dagger_grpc_server",
            "@dagger//:com_google_dagger_dagger_grpc_server_annotations",
            "@dagger//:com_google_dagger_dagger_grpc_server_processor",
            "@maven//:com_google_guava_guava",
            "@maven//:javax_inject_javax_inject",
            "@maven//:javax_annotation_javax_annotation_api",
            ":dagger_plugin",
            "coffee_java_grpc",
            "@com_google_protobuf//:protobuf_java",
            "@com_google_protobuf//:protobuf_java_util",
            "@io_grpc_grpc_java//api",
            "@io_grpc_grpc_java//protobuf",
            "@io_grpc_grpc_java//stub"],
)

#dagger.grpc.server.processor.GrpcServiceProcessor

java_plugin(
    name = "grpc_dagger_plugin",
    processor_class = "dagger.grpc.server.processor.GrpcServiceProcessor",
    generates_api = True,
    deps = [
        "@dagger//:com_google_dagger_dagger",
        "@dagger//:com_google_dagger_dagger_compiler",
        "@dagger//:com_google_dagger_dagger_producers",
        "@dagger//:com_google_dagger_dagger_grpc_server",
        "@dagger//:com_google_dagger_dagger_grpc_server_annotations",
        "@dagger//:com_google_dagger_dagger_grpc_server_processor",
        "@maven//:com_google_guava_guava",
         "@maven//:javax_inject_javax_inject",
    ],
)

java_plugin(
    name = "dagger_plugin",
    processor_class = "dagger.internal.codegen.ComponentProcessor",
    generates_api = True,
    deps = [
        "@dagger//:com_google_dagger_dagger",
        "@dagger//:com_google_dagger_dagger_compiler",
        "@dagger//:com_google_dagger_dagger_producers",
        "@maven//:com_google_guava_guava",
         "@maven//:javax_inject_javax_inject",
    ],
)