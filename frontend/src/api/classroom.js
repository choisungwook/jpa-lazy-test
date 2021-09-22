import http from "./http";

export async function create_classroom(classroom_name) {
  return http.post(
    "api/v1/classroom/",
    {
      classroom_name,
    },
    {
      "content-type": "application/json",
      Accept: "application/json",
    }
  );
}
