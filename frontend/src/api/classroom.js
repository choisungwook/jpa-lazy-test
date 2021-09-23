import http from "./http";

/***
 * 반 생성
 */
export async function create_classroom(classroom_name) {
  return http.post(
    "/api/v1/classroom/",
    {
      classroom_name,
    },
    {
      "content-type": "application/json",
      Accept: "application/json",
    }
  );
}

/***
 * 반 전체 조회
 */
export async function get_all() {
  return http.get(
    "/api/v1/classroom/",
    {},
    {
      "content-type": "application/json",
      Accept: "application/json",
    }
  );
}
