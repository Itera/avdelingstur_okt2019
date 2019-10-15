using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using StackExchange.Redis;

namespace ValuesApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RedisController : ControllerBase
    {
        private readonly IDatabase _db;

        public RedisController(IConnectionMultiplexer connectionMultiplexer)
        {
            _db = connectionMultiplexer.GetDatabase();
        }

        // GET api/redis
        [HttpGet]
        public Task<ActionResult<IEnumerable<string>>> Get()
        {
            return Task.FromResult<ActionResult<IEnumerable<string>>>(new string[] { "value1", "value2" });
        }

        // GET api/redis/5
        [HttpGet("{id}")]
        public async Task<ActionResult<string>> Get(string id)
        {
            var redisValue = await _db.StringGetAsync(id);

            return redisValue.HasValue ? redisValue.ToString() : null;
        }

        // POST api/redis
        [HttpPost]
        public void Post([FromBody] string value)
        {
        }

        // PUT api/redis/5
        [HttpPut("{id}")]
        public async Task Put(string id, [FromBody] string value)
        {
            Console.WriteLine(value);
            await _db.StringSetAsync(id, value);
        }

        // DELETE api/redis/5
        [HttpDelete("{id}")]
        public async Task Delete(string id)
        {
            await _db.KeyDeleteAsync(id);
        }
    }
}